package first.common.util;

import java.util.Locale;

public class MessageSourceManagement {
	private DatabaseMessageSource databaseMessageSource;

	public DatabaseMessageSource getDatabaseMessageSource() {
        return databaseMessageSource;
    }

    public void setDatabaseMessageSource(DatabaseMessageSource databaseMessageSource) {
        this.databaseMessageSource = databaseMessageSource;
    }
 
	public String getMessage(String code, String[] msgArr, Locale locale) {
        return getDatabaseMessageSource().getMessage(code, msgArr, locale);
    }

	public void reload() {
        getDatabaseMessageSource().reload();
    }
}
