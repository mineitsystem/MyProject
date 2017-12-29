package first.common.util;

public class ScreenResolver {
	public static String resolve(Object o) {
        String className = o.getClass().getName();

        className = className.substring(className.lastIndexOf('.') + 1);

        StringBuilder sb = new StringBuilder();
        
        sb.append(className.replaceAll("Controller", ""));

        return sb.toString();
    }

    public static String resolve(Object o, String pageSuffix) {
        String className = o.getClass().getName();

        className = className.substring(className.lastIndexOf('.') + 1);

        StringBuilder sb = new StringBuilder();

        sb.append(className.replaceAll("Controller", ""));
        sb.append(pageSuffix);

        return sb.toString();
    }
}
