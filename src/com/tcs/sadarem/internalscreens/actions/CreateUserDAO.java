package com.tcs.sadarem.internalscreens.actions;

import java.util.ArrayList;
import java.util.HashMap;

public interface CreateUserDAO
{
	public ArrayList rolesToCreateLogin();
	public String createUser(HashMap inputparam);
	public int checkForUserExist(String username);
}
