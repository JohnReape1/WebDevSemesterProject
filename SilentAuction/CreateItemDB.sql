drop TABLE SilentAuction.ITEMS;
CREATE TABLE SilentAuction.Items
(
 Item_ID             INT not null primary key
                        GENERATED ALWAYS AS IDENTITY
                        (START WITH 1, INCREMENT BY 1),
  Item_Name     	VARCHAR(25) NOT NULL,
  List_Price		VARCHAR(25) NOT NULL,
  Category              VARCHAR(25) NOT NULL,
  Highest_Bidder        VARCHAR(25),
  Highest_Bid		VARCHAR(25),
  Minimum_Bid		VARCHAR(25), 
  Approx_Value		VARCHAR(25) NOT NULL,
  Donor                 VARCHAR(25) NOT NULL,
  Author                VARCHAR(25),  
  Angel_Price           VARCHAR(25),
  Payment_Status        VARCHAR(25) DEFAULT 'Not Payed',
  Sold_Status           VARCHAR(25) DEFAULT 'Not Sold'
);
alter table SilentAuction.Items
  add constraint Items_AuthorFK
  FOREIGN KEY (Author) REFERENCES SilentAuction.Users (username);


