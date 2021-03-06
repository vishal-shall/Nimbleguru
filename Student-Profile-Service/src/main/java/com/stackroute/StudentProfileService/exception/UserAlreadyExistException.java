package com.stackroute.StudentProfileService.exception;

public class UserAlreadyExistException extends Exception {
  private String message;
  public UserAlreadyExistException(){

  }
  public UserAlreadyExistException(String message){
      this.message = message;
  }
}
