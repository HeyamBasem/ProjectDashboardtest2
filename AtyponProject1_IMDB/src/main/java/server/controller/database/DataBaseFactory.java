package server.controller.database;

import com.atypon.training.project.common.Constants;
import com.atypon.training.project.server.model.content.BaseContent;
import com.atypon.training.project.server.model.jouranl.Journal;
import com.atypon.training.project.server.model.liscense.BaseLicense;
import com.atypon.training.project.server.model.user.BaseUser;
import server.model.content.BaseContent;
import server.model.user.BaseUser;

public class DataBaseFactory {
    private static DataBase<BaseContent> contentDataBase;
    private static DataBase<BaseUser> userDataBase;
    private static DataBase<BaseLicense> licenseDataBase;
    private static DataBase<Journal> journalDataBase;

    private DataBaseFactory() {
    }

    public synchronized static DataBase<BaseContent> getContentDataBaseInstance(String folder) {
        if (contentDataBase == null) {
            contentDataBase = new DataBase<>(Constants.CACHE_SIZE, folder);
        }
        return contentDataBase;
    }

    public synchronized static DataBase<BaseUser> getUserDataBaseInstance(String folder) {
        if (userDataBase == null) {
            userDataBase = new DataBase<>(Constants.CACHE_SIZE, folder);
        }
        return userDataBase;
    }

    public synchronized static DataBase<BaseLicense> getLicenseDataBaseInstance(String folder) {
        if (licenseDataBase == null) {
            licenseDataBase = new DataBase<>(Constants.CACHE_SIZE, folder);
        }
        return licenseDataBase;
    }


    public synchronized static DataBase<Journal> getJournalDataBaseInstance(String folder) {
        if (journalDataBase == null) {
            journalDataBase = new DataBase<>(Constants.CACHE_SIZE, folder);
        }
        return journalDataBase;
    }
}
