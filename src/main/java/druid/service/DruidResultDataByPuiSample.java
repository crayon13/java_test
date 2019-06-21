package druid.service;

import lombok.Data;

@Data
public class DruidResultDataByPuiSample {
    private String version;
    private String timestamp;
    private Event event;

    @Data
    private class Event {
        private String page_id;
        private String block_id;
        private String age;
        private String gender;

        private Long pay_cnt;
        private Long payment;
        private Long imp;
        private Long click;
    }
}
