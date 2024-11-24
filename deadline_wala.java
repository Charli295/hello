import java.util.*;

public class deadline_wala {

    static class Job {
        int id;        
        int deadline;   
        int profit;    

        public Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static ArrayList<Job> getJobInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of jobs: ");
        int n = scanner.nextInt();

        ArrayList<Job> jobs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter deadline and profit for job " + (i + 1) + ": ");
            int deadline = scanner.nextInt();
            int profit = scanner.nextInt();
            jobs.add(new Job(i + 1, deadline, profit)); 
        }

        return jobs;
    }
       
    public static ArrayList<Integer> selectJobs(ArrayList<Job> jobs) {

        Collections.sort(jobs, Comparator.comparingInt(job -> -job.profit));  //Decreasing order //sorting

        ArrayList<Integer> seq = new ArrayList<>();
        int time = 0;

        for(int i=0;i<jobs.size();i++){
            Job curr = jobs.get(i);
            if(curr.deadline > time){
                seq.add(curr.id);
                time++;
            }
        }

        return seq;
    }

    public static void main(String args[]) {

        ArrayList<Job> jobs = getJobInput();

        ArrayList<Integer> selectedJobs = selectJobs(jobs);

        // Print the result
        System.out.println("Max jobs: " + selectedJobs.size());
        System.out.print("Selected Job IDs: ");
        for (int id : selectedJobs) {
            System.out.print(id + " ");
        }
        System.out.println();
    }
}
