import java.util.Arrays;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;

public class Main
{
                static int minimumArrivalTime,sumCPUBurstTime;
                static int lengthOfEachBlock;
                static final int SCREEN_WIDTH=300,SCREEN_HEIGHT=280;
                static final int rectangleUpperPadding=50,rectangleHeight=100;
                static int numberOfProcesses;
                static int CPUBurstTime[],arrivalTime[];
                static BufferedReader br;
                static Main obj;
               
                Main()
                {
                                this.obj=this;
                }
               
               
                public static void main(String[] args) throws NumberFormatException, IOException
                {
			br=new BufferedReader(new InputStreamReader(System.in));
                                System.out.println("Enter the number of processes : ");
                                numberOfProcesses=Integer.parseInt(br.readLine());
                               
                                 ProcessSJF processSJF = new ProcessSJF(numberOfProcesses);
        			System.out.println("Enter the process details:");
	
			        processSJF.inputProcessData();
	
	 			CPUBurstTime=Arrays.copyOf(processSJF.burstTime, processSJF.burstTime.length);
          		arrivalTime=Arrays.copyOf(processSJF.arrivalTime, processSJF.arrivalTime.length);
                               
                      while(true)
                    {	
				

				        /* asking the user which gantt chart do you want */
                   
                    System.out.println("YOU HAVE THE FOLLOWING CHOICES : \n");
                    System.out.println("1. Non-Preemptive SJF Algorithm");
                    System.out.println("2. Preemptive SJF Algorithm");
                   
                    System.out.println("3.. Exit");
			int choice;
                        System.out.println("ENTER YOUR CHOICE : ");
                        choice=Integer.parseInt(br.readLine());

                        switch(choice)
                        {
                            case 1:
                                drawGanttChartNonPreempt();
				NonPreemptiveSJF nonPreemptiveSJF = new NonPreemptiveSJF(numberOfProcesses);
        			nonPreemptiveSJF.processID = Arrays.copyOf(processSJF.processID, processSJF.processID.length);
        			nonPreemptiveSJF.arrivalTime = Arrays.copyOf(processSJF.arrivalTime, processSJF.arrivalTime.length);
        			nonPreemptiveSJF.burstTime = Arrays.copyOf(processSJF.burstTime, processSJF.burstTime.length);
				System.out.println("\n--- Non-Preemptive SJF ---");
        			nonPreemptiveSJF.calculateSJF();
                                break;
                            case 2:
                                drawGanttChartPreempt();
				PreemptiveSJF preemptiveSJF = new PreemptiveSJF(numberOfProcesses);
        			preemptiveSJF.processID = Arrays.copyOf(processSJF.processID, processSJF.processID.length);
        			preemptiveSJF.arrivalTime = Arrays.copyOf(processSJF.arrivalTime, processSJF.arrivalTime.length);
        			preemptiveSJF.burstTime = Arrays.copyOf(processSJF.burstTime, processSJF.burstTime.length);
				System.out.println("\n--- Preemptive SJF ---");
        			preemptiveSJF.calculateSJF();
                                break;
                            case 3:
                                System.exit(0);
                            default:
                                System.out.println("You Entered a wrong Choice\nPlease fill in the choice again(1-3)...");
                        }
                    }
                    

   

                }
               
                public static void drawGanttChartNonPreempt() throws NumberFormatException, IOException
                {
                    int choice;
                    sumCPUBurstTime=0;


                    /* calculating the sum of all cpu burst time */
                    for(int i=0;i<numberOfProcesses;i++)
                    {
                        sumCPUBurstTime+=CPUBurstTime[i];
                    }

                    /* now the total width of the screen is to be divided into sumCPUBurstTime equal parts */
                    lengthOfEachBlock=SCREEN_WIDTH/sumCPUBurstTime;

                   
                    /*
                     * claculating the minimum arrival time
                     */
                    minimumArrivalTime=Integer.MAX_VALUE;
                    for(int i=0;i<numberOfProcesses;i++)
                    {
                                if(minimumArrivalTime>arrivalTime[i])
                                                minimumArrivalTime=arrivalTime[i];
                    }
                   	drawGanttChartForNonPreemptiveSJF();	
                  
                }
		public static void drawGanttChartPreempt() throws NumberFormatException, IOException
                {
                    int choice;
                    sumCPUBurstTime=0;


                    /* calculating the sum of all cpu burst time */
                    for(int i=0;i<numberOfProcesses;i++)
                    {
                        sumCPUBurstTime+=CPUBurstTime[i];
                    }

                    /* now the total width of the screen is to be divided into sumCPUBurstTime equal parts */
                    lengthOfEachBlock=SCREEN_WIDTH/sumCPUBurstTime;

                   
                    /*
                     * claculating the minimum arrival time
                     */
                    minimumArrivalTime=Integer.MAX_VALUE;
                    for(int i=0;i<numberOfProcesses;i++)
                    {
                                if(minimumArrivalTime>arrivalTime[i])
                                                minimumArrivalTime=arrivalTime[i];
                    }
                   	drawGanttChartForPreemptiveSJF();	
                  
                }
               
                public static void drawGanttChartForNonPreemptiveSJF()
                {
                                new FrameForNonPreemptiveSJF(obj);
                }
                public static void drawGanttChartForPreemptiveSJF()
                {
                                new FrameForPreemptiveSJF(obj);
                }
}



class FrameForNonPreemptiveSJF extends JFrame
{
                int CPUBurstTime[];
                Main obj;
                FrameForNonPreemptiveSJF(Main obj)
                {
                                super("Non preemptive SJF");
                                this.obj=obj;
                                this.setVisible(true);
                                this.setSize(obj.SCREEN_WIDTH+10, obj.SCREEN_HEIGHT);
                                CPUBurstTime=obj.CPUBurstTime.clone();
                                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                private void getDefaultCloseOperation(int exitOnClose) {
                }
                @Override
                public void paint(Graphics g)
                {
                                super.paint(g);
                               
                                this.getContentPane().setBackground(Color.white);
                                int currentTime=obj.minimumArrivalTime;
                               
                                CPUBurstTime=obj.CPUBurstTime.clone();
               
                                int i,j,min,mini = 0;
                    int leftStart=50;
                   
                    g=this.getContentPane().getGraphics();
                    g.drawString(""+obj.minimumArrivalTime,leftStart,obj.rectangleUpperPadding+obj.rectangleHeight+20);
                   
                    for(j=0;j<obj.numberOfProcesses;j++)
                    {
                        min=Integer.MAX_VALUE;
                        for(i=0;i<obj.numberOfProcesses;i++)
                        {
                            if(min>CPUBurstTime[i] && obj.arrivalTime[i]<=currentTime)
                            {
                                min=CPUBurstTime[i];
                                mini=i;
                            }
                        }
                       
                        g=this.getContentPane().getGraphics();
                        g.drawRect(leftStart,obj.rectangleUpperPadding,obj.lengthOfEachBlock*obj.CPUBurstTime[mini],obj.rectangleHeight);
                        g.drawString("P"+(mini+1),leftStart+5,obj.rectangleUpperPadding+50);
                        leftStart+=obj.lengthOfEachBlock*obj.CPUBurstTime[mini];
                       
                        currentTime+=obj.CPUBurstTime[mini];
                        g.drawString(""+currentTime,leftStart,obj.rectangleUpperPadding+obj.rectangleHeight+20);
                       
                        CPUBurstTime[mini]=Integer.MAX_VALUE;
                       
                    }
                }
}


class FrameForPreemptiveSJF extends JFrame
{
                int CPUBurstTime[];
                Main obj;
                FrameForPreemptiveSJF(Main obj)
                {
                                super("Preemptive SJF");
                                this.obj=obj;
                                this.setVisible(true);
                                this.setSize(obj.SCREEN_WIDTH+10, obj.SCREEN_HEIGHT);
                                CPUBurstTime=obj.CPUBurstTime.clone();
                                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
               
                private void getDefaultCloseOperation(int exitOnClose) {
                }

                @Override
                public void paint(Graphics g)
                {
                                super.paint(g);
                                CPUBurstTime=obj.CPUBurstTime.clone();
                                this.getContentPane().setBackground(Color.white);
                                int currentTime=obj.minimumArrivalTime;
                                int min,mini=0,prevmini = 0;
                                int leftStart=50;
                               
                                g=this.getContentPane().getGraphics();
                    g.drawString(""+obj.minimumArrivalTime,leftStart,obj.rectangleUpperPadding+obj.rectangleHeight+20);
                   
                                for(int j=0;j<obj.sumCPUBurstTime;j++)
                                {
                                                min=Integer.MAX_VALUE;
                        for(int i=0;i<obj.numberOfProcesses;i++)
                        {
                            if(min>CPUBurstTime[i] && obj.arrivalTime[i]<=currentTime && CPUBurstTime[i]!=0)
                            {
                                min=CPUBurstTime[i];
                                mini=i;
                            }
                        }
                        if(j==0)
                prevmini=mini;
       
                        if(prevmini!=mini || j==obj.sumCPUBurstTime-1)
                        {
                                        g=this.getContentPane().getGraphics();
                                        if(j==obj.sumCPUBurstTime-1)
                                                        g.drawRect(leftStart,obj.rectangleUpperPadding,obj.lengthOfEachBlock*(currentTime+1),obj.rectangleHeight);
                                        else
                                                        g.drawRect(leftStart,obj.rectangleUpperPadding,obj.lengthOfEachBlock*(currentTime),obj.rectangleHeight);
                                        g.drawString("P"+(prevmini+1),leftStart+5,obj.rectangleUpperPadding+50);
                                        leftStart+=obj.lengthOfEachBlock*currentTime;
                                        if(j==obj.sumCPUBurstTime-1)
                                                        g.drawString(""+(currentTime+1),leftStart+obj.lengthOfEachBlock,obj.rectangleUpperPadding+obj.rectangleHeight+20);
                                        else
                                                g.drawString(""+(currentTime),leftStart,obj.rectangleUpperPadding+obj.rectangleHeight+20);
                        }
                        currentTime++;
                       
                        CPUBurstTime[mini]--;
                        prevmini=mini;
                                }
                }             
}

class ProcessSJF {
    protected String[] processID;
    protected int[] arrivalTime;
    protected int[] burstTime;

    public ProcessSJF(int numberOfProcesses) {
        processID = new String[numberOfProcesses];
        arrivalTime = new int[numberOfProcesses];
        burstTime = new int[numberOfProcesses];
    }

    public void inputProcessData() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < processID.length; i++) {
            System.out.print("Enter Process ID: ");
            processID[i] = scanner.next();
            System.out.print("Enter Arrival Time: ");
            arrivalTime[i] = scanner.nextInt();
            System.out.print("Enter Burst Time: ");
            burstTime[i] = scanner.nextInt();
        }
    }

    public void displayProcessData(int[] completionTime, int[] turnaroundTime, int[] waitingTime) {
        System.out.println("Process ID\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < processID.length; i++) {
            System.out.printf("%s\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n",
                    processID[i], arrivalTime[i], burstTime[i], completionTime[i], turnaroundTime[i], waitingTime[i]);
        }
    }

    public float calculateAverage(int[] data) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return (float) sum / data.length;
    }
}

class NonPreemptiveSJF extends ProcessSJF {
    public NonPreemptiveSJF(int numberOfProcesses) {
        super(numberOfProcesses);
    }

    public void calculateSJF() {
        // Create a copy of the original arrays and sort them based on arrival time
        int[] arrivalTimeCopy = Arrays.copyOf(arrivalTime, arrivalTime.length);
        int[] burstTimeCopy = Arrays.copyOf(burstTime, burstTime.length);
        int[] completionTime = new int[processID.length];
        int[] turnaroundTime = new int[processID.length];
        int[] waitingTime = new int[processID.length];

        int currentTime = 0;
        int completedProcesses = 0;

        while (completedProcesses < processID.length) {
            int shortestJobIndex = -1;
            int shortestJobBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < processID.length; i++) {
                if (arrivalTimeCopy[i] <= currentTime && burstTimeCopy[i] < shortestJobBurstTime && burstTimeCopy[i] > 0) {
                    shortestJobIndex = i;
                    shortestJobBurstTime = burstTimeCopy[i];
                }
            }

            if (shortestJobIndex == -1) {
                currentTime++;
                continue;
            }

            currentTime += burstTimeCopy[shortestJobIndex];
            completionTime[shortestJobIndex] = currentTime;
            turnaroundTime[shortestJobIndex] = completionTime[shortestJobIndex] - arrivalTimeCopy[shortestJobIndex];
            waitingTime[shortestJobIndex] = turnaroundTime[shortestJobIndex] - burstTime[shortestJobIndex];
            burstTimeCopy[shortestJobIndex] = 0;
            completedProcesses++;
        }

        displayProcessData(completionTime, turnaroundTime, waitingTime);
        System.out.println("Average Turnaround Time (Non-Preemptive SJF): " + calculateAverage(turnaroundTime));
        System.out.println("Average Waiting Time (Non-Preemptive SJF): " + calculateAverage(waitingTime));
    }
}

class PreemptiveSJF extends ProcessSJF {
    public PreemptiveSJF(int numberOfProcesses) {
        super(numberOfProcesses);
    }

    public void calculateSJF() {
        // Create a copy of the original arrays to preserve the original data
        int[] burstTimeCopy = Arrays.copyOf(burstTime, burstTime.length);
        int[] completionTime = new int[processID.length];
        int[] turnaroundTime = new int[processID.length];
        int[] waitingTime = new int[processID.length];

        int currentTime = 0;
        int completedProcesses = 0;

        while (completedProcesses < processID.length) {
            int shortestJobIndex = -1;
            int shortestJobBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < processID.length; i++) {
                if (arrivalTime[i] <= currentTime && burstTimeCopy[i] < shortestJobBurstTime && burstTimeCopy[i] > 0) {
                    shortestJobIndex = i;
                    shortestJobBurstTime = burstTimeCopy[i];
                }
            }

            if (shortestJobIndex == -1) {
                currentTime++;
                continue;
            }

            burstTimeCopy[shortestJobIndex]--;
            currentTime++;

            if (burstTimeCopy[shortestJobIndex] == 0) {
                completionTime[shortestJobIndex] = currentTime;
                turnaroundTime[shortestJobIndex] = completionTime[shortestJobIndex] - arrivalTime[shortestJobIndex];
                waitingTime[shortestJobIndex] = turnaroundTime[shortestJobIndex] - burstTime[shortestJobIndex];
                completedProcesses++;
            }
        }

        displayProcessData(completionTime, turnaroundTime, waitingTime);
        System.out.println("Average Turnaround Time (Preemptive SJF): " + calculateAverage(turnaroundTime));
        System.out.println("Average Waiting Time (Preemptive SJF): " + calculateAverage(waitingTime));
    }
}
