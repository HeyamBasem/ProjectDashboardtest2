package operations;

import java.io.Serializable;

public enum Operation implements Serializable {
    CreateUser, UpdateUser, DeleteUser, GetUser, LoginUser,
    CreateJournal, UpdateJournal, DeleteJournal, GetJournal,
    CreateContent, UpdateContent, DeleteContent, GetContent,
    CreateLicense, UpdateLicense, DeleteLicense, GetLicense
}
