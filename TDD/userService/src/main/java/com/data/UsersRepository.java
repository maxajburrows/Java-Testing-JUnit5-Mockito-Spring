package com.data;

import com.model.User;

public interface UsersRepository {
    boolean save(User user);
}
