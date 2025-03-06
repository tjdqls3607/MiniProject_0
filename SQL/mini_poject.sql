CREATE TABLE member (
    member_id INT AUTO_INCREMENT PRIMARY KEY,	-- 회원 ID (기본 키)
    name VARCHAR(50) NOT NULL,	-- 회원 이름
    phone_number VARCHAR(15) UNIQUE NOT NULL,	-- 회원 전화번호 (중복 방지)
    birth_date DATE NOT NULL	-- 회원 생년월일
);

CREATE TABLE model (	
    model_id INT AUTO_INCREMENT PRIMARY KEY,	-- 휴대폰 기종 ID (기본 키)
    model_name VARCHAR(50) NOT NULL,	-- 휴대폰 기종명
    manufacturer VARCHAR(50) NOT NULL,	-- 제조사 (예: 삼성, 애플)
    price DECIMAL(10,2) NOT NULL	-- 출고가 (소수점 두 자리까지)
);

CREATE TABLE stock (
    stock_id INT AUTO_INCREMENT PRIMARY KEY,	-- 재고 ID (기본 키)
    model_id INT NOT NULL,	-- 휴대폰 기종 ID (외래 키)
    quantity INT NOT NULL,	-- 현재 재고 수량
    FOREIGN KEY (model_id) REFERENCES model(model_id) ON DELETE CASCADE	
);

CREATE TABLE membership (
    membership_id INT AUTO_INCREMENT PRIMARY KEY,	-- 멤버십 ID (기본 키)
    member_id INT NOT NULL,		-- 회원 ID (외래 키) 
    level ENUM('BRONZE', 'SILVER', 'GOLD', 'PLATINUM', 'DIAMOND') NOT NULL,	-- 멤버십 등급 
    discount_rate DECIMAL(5,2) NOT NULL,	-- 할인율 (% 단위, 예: 10.00 → 10%)
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE sales (
    sales_id INT AUTO_INCREMENT PRIMARY KEY,	-- 판매 ID (기본 키)
    member_id INT NOT NULL,		-- 구매 회원 ID (외래 키)
    model_id INT NOT NULL,		-- 구매한 휴대폰 기종 ID (외래 키)
    sale_date DATETIME DEFAULT CURRENT_TIMESTAMP,	-- 판매 일자 (기본값: 현재 시간)
    final_price DECIMAL(10,2) NOT NULL,	-- 최종 결제 금액 (할인 적용 후 가격)
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    FOREIGN KEY (model_id) REFERENCES model(model_id) ON DELETE CASCADE
);

CREATE TABLE inventory_log (		-- 입출고 기록 테이블
    log_id INT AUTO_INCREMENT PRIMARY KEY,		-- 입출고 기록 ID (기본 키)
    model_id INT NOT NULL,			-- 휴대폰 기종 ID (외래 키)
    change_quantity INT NOT NULL,	-- 변동 수량 (+값: 입고, -값: 출고)	
    change_type ENUM('IN', 'OUT') NOT NULL,	-- 입출고 유형 ('IN': 입고, 'OUT': 출고)
    change_date DATETIME DEFAULT CURRENT_TIMESTAMP,	-- 입출고 발생 일자
    FOREIGN KEY (model_id) REFERENCES model(model_id) ON DELETE CASCADE
);
