package name.nepavel.commandline;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CommandLineProcessorTest {

    @Test
    @Ignore
    public void get() {
        String[] args = new String[] {"-par", "val", "-par2", "val2"};
        CommandLineProcessor processor = new CommandLineProcessor(args);
        assertEquals("Требуемый параметр отсутствует!", "val", processor.get("-par"));
        assertEquals("Требуемый параметр отсутствует!", "val2", processor.get("-par2"));
        assertNull("Лишний параметр присутствует!", processor.get("-par3"));
    }

    @Test
    public void is() {
        String[] args = new String[] {"-a", "-b"};
        CommandLineProcessor processor = new CommandLineProcessor(args);
        assertTrue("Требуемый флаг отсутствует!", processor.is("-a"));
        assertTrue("Требуемый флаг отсутствует!", processor.is("-b"));
        assertFalse("Лишний флаг присутствует!", processor.is("-с"));
    }

    @Test
    public void list() {
        String[] args = new String[] {"a", "b"};
        List<String> argList = Arrays.asList(args);
        CommandLineProcessor processor = new CommandLineProcessor(args);
        assertThat(processor.list(), equalTo(argList));
    }

    @Test
    public void flagsAndList() {
        List<String> flags = Arrays.asList("-i", "-s");
        List<String> list = Arrays.asList("a", "b", "c", "d");
        List<String> args = new ArrayList<>();
        args.addAll(flags);
        args.addAll(list);
        CommandLineProcessor processor = new CommandLineProcessor(args.toArray(new String[0]));
        assertTrue("Требуемый флаг отсутствует!", processor.is("-i"));
        assertTrue("Требуемый флаг отсутствует!", processor.is("-s"));
        assertThat("Список не совпадает с исходным!", processor.list(), equalTo(list));
    }

    @Test(expected = IllegalArgumentException.class)
    public void duplicatedFlag() {
        String[] args = new String[] {"-i", "-s", "-i"};
        CommandLineProcessor processor = new CommandLineProcessor(args);
    }

    private CustomTypeSafeMatcher<List<String>> equalTo(List<String> argList) {
        return new CustomTypeSafeMatcher<List<String>>(argList.toString()) {
            @Override
            protected boolean matchesSafely(List<String> strings) {
                if (strings.size() != argList.size())
                    return false;
                for (int i = 0; i < strings.size(); i++) {
                    if (!strings.get(i).equals(argList.get(i)))
                        return false;
                }
                return true;
            }
        };
    }

}