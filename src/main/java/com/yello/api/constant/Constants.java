package com.yello.api.constant;

/**
 * Holds all constants. Make sure the constant name in all upper case letters
 * with underscores for spaces.
 * 
 * @author kaushal 
 * @version 1.0
 * @since 27.03.2018
 */
public interface Constants {
	
    enum STATUS {
        SUCCESS, ERROR, INVALID_REQUEST;
    }
    String DB_ERROR = "Data unavailable";
    String DB_INSERTION_ERROR = "Data Insertion problem";
    String DB_UPDATE_ERROR = "Data Updation problem";
    String DB_DELETE_ERROR = "Data Delete problem";
    String DB_FETCH_ERROR = "Data Fetch problem";
    String DB_INSERT_DATA_USER_INFO = "Data_Inserted to User_Info :";
    // Collection name
    String USER_INFO_COLLECTION = "User_Info";
    //  field name
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    // parameter value
    String USER_INFO = "UserInfo";
  
    String INSERT_TO = "insert-to";

}
