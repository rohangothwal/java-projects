import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * Problem statements
 * 
 * 1. How to select the first job?
 * Sol. Sort the list in order of end time & pick the first one.
 * 2. How to select the subsequent jobs?
 * Sol. Take the next job from the sorted list which has start date greater than the 
 * end date of previous item in the new list.
 */

public class ShortestJobFirst {

	private static class Job implements Comparable<Job>{
		private int jobId;
		private int startTime;
		private int endTime;
		public int compareTo(Job o) {
			return this.endTime-o.endTime;
		}
		public int getJobId() {
			return jobId;
		}
		public void setJobId(int jobId) {
			this.jobId = jobId;
		}
		public int getStartTime() {
			return startTime;
		}
		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}
		public int getEndTime() {
			return endTime;
		}
		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}
		@Override
		public String toString() {
			return jobId+"\t"+startTime+"\t"+endTime;
		}
		
	}
	public static void main(String[] args) throws Exception {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/ShortestJobFirst.txt")));
        String line = bufferedReader.readLine();
        List<Job> jobList = new ArrayList<ShortestJobFirst.Job>();
        while (null!=line){
        	StringTokenizer tokens = new StringTokenizer(line, "\t");
        	Job job = new Job();
        	jobList.add(job);
			job.setJobId(Integer.parseInt(tokens.nextToken()));
			job.setStartTime(Integer.parseInt(tokens.nextToken()));
			job.setEndTime(Integer.parseInt(tokens.nextToken()));
        	line = bufferedReader.readLine();
        }
        Collections.sort(jobList);
        bufferedReader.close();
        
        List<Job> finalList = new ArrayList<ShortestJobFirst.Job>();
        int i = 0;
        finalList.add(jobList.get(i));
        for(Job job:jobList){
        	//System.out.println(job.toString());
        	if (finalList.get(i).getEndTime()<job.getStartTime()){
        		finalList.add(job);
        		i++;
        	}
        }
        System.out.println(finalList.size());
        for(Job job:finalList){
        	System.out.println(job.toString());
        }

	}

}
