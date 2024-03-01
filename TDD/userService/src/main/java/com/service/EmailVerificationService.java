package com.service;

import com.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
