use seong;

CREATE TABLE `Users` (
                         `UserId` varchar(50) NOT NULL COMMENT '아이디',
                         `UserName` varchar(50) NOT NULL COMMENT '이름',
                         `UserPassword` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `UserBirth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                         `UserAuth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `UserPoint` int NOT NULL COMMENT 'default : 1000000',
                         `CreatedAt` datetime NOT NULL COMMENT '가입일자',
                         `LatestLogin_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                         PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

CREATE TABLE `Categories` (
                              `CategoryID` INT NOT NULL AUTO_INCREMENT COMMENT '카테고리 ID',
                              `CategoryName` VARCHAR(100) NOT NULL COMMENT '카테고리 이름',
                              PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 카테고리';

CREATE TABLE `Products` (
                            `ProductID` INT NOT NULL AUTO_INCREMENT COMMENT '상품 ID',
                            `ModelNumber` VARCHAR(50) NOT NULL UNIQUE COMMENT '모델 번호',
                            `ModelName` VARCHAR(100) NOT NULL COMMENT '모델 이름',
                            `ProductImage` VARCHAR(255) COMMENT '상품 이미지 경로',
                            `UnitPrice` INT NOT NULL COMMENT '가격',
                            `Description` TEXT COMMENT '상품 설명',
                            `CategoryID` INT,
                            PRIMARY KEY (`ProductID`),
                            FOREIGN KEY (`CategoryID`) REFERENCES `Categories`(`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';

CREATE TABLE `Cart` (
                        `UserID` VARCHAR(50) NOT NULL COMMENT '회원 ID',
                        `ProductID` INT NOT NULL COMMENT '상품 ID',
                        `Quantity` INT NOT NULL DEFAULT 1 COMMENT '수량',
                        `CreatedAt` DATETIME NOT NULL COMMENT '등록일',
                        PRIMARY KEY (`UserID`, `ProductID`),
                        FOREIGN KEY (`UserID`) REFERENCES `Users`(`UserID`) ON DELETE CASCADE,
                        FOREIGN KEY (`ProductID`) REFERENCES `Products`(`ProductID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니';

CREATE TABLE `Orders` (
                          `OrderID` INT NOT NULL AUTO_INCREMENT COMMENT '주문 ID',
                          `UserID` VARCHAR(50) NOT NULL COMMENT '회원 ID',
                          `OrderDate` DATETIME NOT NULL COMMENT '주문일자',
                          `ShipAddress` VARCHAR(255) NOT NULL COMMENT '배송지',
                          `TotalPrice` INT NOT NULL COMMENT '총 주문금액',
                          PRIMARY KEY (`OrderID`),
                          FOREIGN KEY (`UserID`) REFERENCES `Users`(`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문';

CREATE TABLE `OrderDetails` (
                                `OrderDetailID` INT NOT NULL AUTO_INCREMENT COMMENT '주문상세 ID',
                                `OrderID` INT NOT NULL COMMENT '주문 ID',
                                `ProductID` INT NOT NULL COMMENT '상품 ID',
                                `Quantity` INT NOT NULL COMMENT '주문 수량',
                                `UnitPrice` INT NOT NULL COMMENT '상품 단가',
                                PRIMARY KEY (`OrderDetailID`),
                                FOREIGN KEY (`OrderID`) REFERENCES `Orders`(`OrderID`) ON DELETE CASCADE,
                                FOREIGN KEY (`ProductID`) REFERENCES `Products`(`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 상세';


CREATE TABLE `OrderAddresses` (
                                  `AddressID` INT NOT NULL AUTO_INCREMENT COMMENT '주소 PK',
                                  `OrderID` INT NOT NULL COMMENT '주문 ID',
                                  `ZipCode` VARCHAR(20) NOT NULL COMMENT '우편번호',
                                  `Address` VARCHAR(255) NOT NULL COMMENT '주소',
                                  `AddressDetail` VARCHAR(255) DEFAULT NULL COMMENT '상세 주소',
                                  PRIMARY KEY (`AddressID`),
                                  CONSTRAINT `fk_orderaddresses_order`
                                      FOREIGN KEY (`OrderID`) REFERENCES `Orders`(`OrderID`)
                                          ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci
    COMMENT='주문 배송지';


CREATE TABLE `PointHistory` (
                                `PointHistoryID` INT NOT NULL AUTO_INCREMENT COMMENT '포인트 이력 PK',
                                `UserID` VARCHAR(50) NOT NULL COMMENT '회원 ID',
                                `OrderID` INT NULL DEFAULT NULL COMMENT '주문 ID(선택적)',
                                `Points` INT NOT NULL COMMENT '포인트 증감',
                                `TransactionType` VARCHAR(50) NOT NULL COMMENT '타입: EARN/USE 등',
                                `TransactionDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '거래 일시',
                                PRIMARY KEY (`PointHistoryID`),
                                KEY `idx_pointhistory_userid_date` (`UserID`, `TransactionDate`),
                                CONSTRAINT `fk_pointhistory_user`
                                    FOREIGN KEY (`UserID`) REFERENCES `Users`(`UserID`)
                                        ON DELETE CASCADE,
                                CONSTRAINT `fk_pointhistory_order`
                                    FOREIGN KEY (`OrderID`) REFERENCES `Orders`(`OrderID`)
                                        ON DELETE SET NULL
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci
    COMMENT='포인트 이력';

