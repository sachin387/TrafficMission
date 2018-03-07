package com.Lengaburu.Traffic;

import java.util.Comparator;

import com.Lengaburu.Traffic.DTO.*;

public class TravellTimeComparator implements Comparator<TrafficDTO> {

	@Override
	public int compare(TrafficDTO o1, TrafficDTO o2) {
		// TODO Auto-generated method stub
		if (o1.getTravellTime() > o2.getTravellTime()) {
			return 1;
		} else {
			return -1;
		}
	}

}
