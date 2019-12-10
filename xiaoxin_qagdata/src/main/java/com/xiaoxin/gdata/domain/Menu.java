package com.xiaoxin.gdata.domain;

public class Menu {
    /*
 CREATE TABLE `menu_calendar` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_remark` varchar(255) DEFAULT NULL COMMENT '菜单备注',
  `menu_type` int(1) DEFAULT '1' COMMENT '菜单类型，1=普通工作餐，2=超级工作餐, 3=召牌工作餐',
  `seller_id` bigint(20) NOT NULL COMMENT '商家ID',
  `seller_name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `seller_lng` decimal(15,7) DEFAULT NULL COMMENT '商家经度',
  `seller_lat` decimal(15,7) DEFAULT NULL COMMENT '商家纬度',
  `sell_start_time` timestamp NULL DEFAULT NULL COMMENT '售卖开始时间',
  `sell_end_time` timestamp NULL DEFAULT NULL COMMENT '售卖结束时间',
  `distance` int(8) DEFAULT NULL COMMENT '可售距离',
  `spu_id` bigint(20) DEFAULT NULL,
  `spu_name` varchar(255) DEFAULT NULL,
  `spu_img_url` varchar(500) DEFAULT NULL,
  `product_count` int(5) DEFAULT NULL COMMENT '商品数量',
  `sell_price` decimal(10,2) DEFAULT NULL COMMENT '售价',
  `cost_price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
  `fix_group_price` decimal(10,2) DEFAULT NULL,
  `min_price` decimal(10,2) DEFAULT NULL,
  `min_group_price` decimal(10,2) DEFAULT NULL,
  `his_sales` int(8) DEFAULT NULL COMMENT '历史销量',
  `sort` int(2) DEFAULT '1' COMMENT '菜单排序，9=置顶，2=新上线，1=普通，0=置底',
  `status` int(1) DEFAULT '1' COMMENT '1=上线，0=下线',
  `cfg_id` bigint(20) DEFAULT NULL COMMENT '菜单对应的库存配置',
  `is_new` int(1) DEFAULT NULL COMMENT '是否为新上线的菜单，1=新，0=不是新',
  `tag` varchar(500) DEFAULT NULL COMMENT '菜单标记',
  `category_id` bigint(20) DEFAULT NULL COMMENT '品类，如：炒饭、盖饭、轻食 只放品类的ID',
  `meal_type` varchar(100) DEFAULT NULL COMMENT '餐品类型：1=早餐，2=午餐，3=晚餐，4= 下午茶 多个用逗号隔开',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `booking_type` varchar(32) DEFAULT NULL COMMENT '预定类型(自选组合、套餐……) 对应spu的bookingType',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '菜单的来源规则ID',
  `origin_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `price_cfg_id` bigint(20) DEFAULT NULL COMMENT '价格方案ID',
  `is_delete` int(1) DEFAULT '0' COMMENT '1=删除，0=未删除',
  `business_status` int(1) DEFAULT '1' COMMENT '1=营业，0=歇业',
  PRIMARY KEY (`menu_id`),
  KEY `menu_spu_id` (`spu_id`),
  KEY `menu_seller_id` (`seller_id`),
  KEY `menu_rule_id` (`rule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100002254 DEFAULT CHARSET=utf8mb4 COMMENT='菜单日历';
  */
    private int menu_id ;
    private String menu_name ;
    private String menu_remark ;
    private int menu_type ;
    private long seller_id ;
    private  String seller_name ;
    private String seller_lng ;
    private String seller_lat ;
    private String sell_start_time ;
    private String sell_end_time ;
    private int distance ;
    private long spu_id ;
    private String spu_name ;
    private String spu_img_url ;
    private int product_count ;
    private double sell_price ;
    private double cost_price ;
    private double fix_group_price ;
    private double min_price ;
    private double min_group_price ;
    private int his_sales ;
    private int sort ;
    private int status ;
    private long cfg_id ;
    private int is_new ;
    private String tag ;
    private long category_id ;
    private String meal_type ;
    private String create_time ;
    private String update_time ;
    private String booking_type ;
    private long rule_id ;
    private double origin_price ;
    private long price_cfg_id ;
    private int is_delete ;
    private int business_status ;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_remark() {
        return menu_remark;
    }

    public void setMenu_remark(String menu_remark) {
        this.menu_remark = menu_remark;
    }

    public int getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(int menu_type) {
        this.menu_type = menu_type;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_lng() {
        return seller_lng;
    }

    public void setSeller_lng(String seller_lng) {
        this.seller_lng = seller_lng;
    }

    public String getSeller_lat() {
        return seller_lat;
    }

    public void setSeller_lat(String seller_lat) {
        this.seller_lat = seller_lat;
    }

    public String getSell_start_time() {
        return sell_start_time;
    }

    public void setSell_start_time(String sell_start_time) {
        this.sell_start_time = sell_start_time;
    }

    public String getSell_end_time() {
        return sell_end_time;
    }

    public void setSell_end_time(String sell_end_time) {
        this.sell_end_time = sell_end_time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public long getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(long spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSpu_img_url() {
        return spu_img_url;
    }

    public void setSpu_img_url(String spu_img_url) {
        this.spu_img_url = spu_img_url;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public double getCost_price() {
        return cost_price;
    }

    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }

    public double getFix_group_price() {
        return fix_group_price;
    }

    public void setFix_group_price(double fix_group_price) {
        this.fix_group_price = fix_group_price;
    }

    public double getMin_price() {
        return min_price;
    }

    public void setMin_price(double min_price) {
        this.min_price = min_price;
    }

    public double getMin_group_price() {
        return min_group_price;
    }

    public void setMin_group_price(double min_group_price) {
        this.min_group_price = min_group_price;
    }

    public int getHis_sales() {
        return his_sales;
    }

    public void setHis_sales(int his_sales) {
        this.his_sales = his_sales;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCfg_id() {
        return cfg_id;
    }

    public void setCfg_id(long cfg_id) {
        this.cfg_id = cfg_id;
    }

    public int getIs_new() {
        return is_new;
    }

    public void setIs_new(int is_new) {
        this.is_new = is_new;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getBooking_type() {
        return booking_type;
    }

    public void setBooking_type(String booking_type) {
        this.booking_type = booking_type;
    }

    public long getRule_id() {
        return rule_id;
    }

    public void setRule_id(long rule_id) {
        this.rule_id = rule_id;
    }

    public double getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(double origin_price) {
        this.origin_price = origin_price;
    }

    public long getPrice_cfg_id() {
        return price_cfg_id;
    }

    public void setPrice_cfg_id(long price_cfg_id) {
        this.price_cfg_id = price_cfg_id;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(int business_status) {
        this.business_status = business_status;
    }
}
