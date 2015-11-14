drop TABLE SilentAuction.USERS;
CREATE TABLE SilentAuction.Users
(
  "User_ID" INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
  First_Name                            VARCHAR(25) NOT NULL,
  Last_Name				VARCHAR(25) NOT NULL,
  Username				VARCHAR(25) UNIQUE,
  Password				VARCHAR(25) NOT NULL,
  Email					VARCHAR(25) NOT NULL,
  Street 				VARCHAR(25) NOT NULL,
  City					VARCHAR(25) NOT NULL,
  State_                                VARCHAR(25) NOT NULL,
  Zip					VARCHAR(25) NOT NULL,
  Home_Phone                            VARCHAR(25) NOT NULL,
  Cell_Phone                            VARCHAR(25) NOT NULL,
  Permission_Level                      VARCHAR(25) NOT NULL,
  Question                              VARCHAR(25) NOT NULL,
  Answer                                VARCHAR(25) NOT NULL
);
