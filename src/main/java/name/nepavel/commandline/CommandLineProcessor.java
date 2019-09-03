package name.nepavel.commandline;

import java.util.*;

public class CommandLineProcessor {
    private final Map<String, String> params = new HashMap<>();
    private final Set<String> flags = new HashSet<>();
    private final List<String> list = new ArrayList<>();

    /**
     * Если список входных параметров невалидный, кидает исключение
     * @param args список входных параметров
     * @throws IllegalArgumentException
     */
    public CommandLineProcessor(String[] args) throws IllegalArgumentException {
        //тут логика разбора входных параметров и наполнение params, flags, list
        //пиши код только тут, больше нигде
    }

    /**
     * Возвращает значение параметра
     * @param param требуемый параметр
     * @return значение параметра, или null, если параметр отсутствует
     */
    public String get(String param) {
//        return params.get(param);
        throw new UnsupportedOperationException("Пока не реализовано");
    }

    /**
     * Проверяет наличие флага
     * @param flag требуемый флаг
     * @return true при наличии флага, иначе false
     * e
     */
    public boolean is(String flag) {
        return flags.contains(flag);
    }

    /**
     * Возвращает список аргументов, которые не являются ни параметрами, ни флагами. Возвращаемый список является
     * копией и не связан с оригинальным, что исключает возможность вмешательства во внутреннее состояние класса
     * @return
     */
    public List<String> list() {
        List<String> result = new ArrayList<>(list.size());
        Collections.copy(result, list);
        return result;
    }
}
