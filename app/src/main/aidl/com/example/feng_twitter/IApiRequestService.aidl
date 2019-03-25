// IApiRequestService.aidl
package com.example.feng_twitter;

// Declare any non-default types here with import statements

interface IApiRequestService {
      Map  authenticate(String userName,String passWord);
      Map getListOfPost();
     Map postTweet();
}
