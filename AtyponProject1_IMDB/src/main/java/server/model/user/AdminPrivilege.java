package server.model.user;

public enum AdminPrivilege {
    BasePrivilege, CreateUserPrivilege, WrongChoice;

    public static AdminPrivilege getPrivilege(int choice) {
        switch (choice) {
            case 0:
                return BasePrivilege;
            case 1:
                return CreateUserPrivilege;
            default:
                return WrongChoice;
        }
    }
}
