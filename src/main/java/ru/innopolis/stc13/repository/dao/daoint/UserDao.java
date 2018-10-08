package ru.innopolis.stc13.repository.dao.daoint;

import ru.innopolis.stc13.repository.pojo.User;

public interface UserDao {

    User getUserByLogin(String login);
}
