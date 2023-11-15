package com.aleleone.WOD.Randomizer.presentation;

public record ChangePasswordRequest(String currentPassword, String newPassword, String confirmationPassword) {

}
