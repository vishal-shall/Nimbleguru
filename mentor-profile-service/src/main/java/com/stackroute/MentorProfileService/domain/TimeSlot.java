package com.stackroute.MentorProfileService.domain;

import javax.persistence.Entity;
@Entity
public class TimeSlot
{
        int startTime;
        int endTime;

        public int getStartTime() {
                return startTime;
        }

        public void setStartTime(int startTime) {
                this.startTime = startTime;
        }

        public int getEndTime() {
                return endTime;
        }

        public void setEndTime(int endtime) {
                endTime = endtime;
        }

}
