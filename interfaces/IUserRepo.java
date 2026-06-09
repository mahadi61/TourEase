package interfaces;

import entity.*;

public interface IUserRepo {
    User checkLogin(String username, String password);

    boolean changePassword(String username, String newPass);
}