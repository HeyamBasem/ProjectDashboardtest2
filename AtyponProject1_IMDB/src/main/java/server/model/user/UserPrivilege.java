package server.model.user;

public enum UserPrivilege {
    BasePrivilege, ModifyContentPrivilege, WrongChoice;

    public static UserPrivilege getPrivilege(int privilege) {
        switch (privilege) {
            case 0:
                return BasePrivilege;
            case 1:
                return ModifyContentPrivilege;
            default:
                return WrongChoice;
        }
    }
}
