Shortest Job First (SJF)

This Java program demonstrates the Shortest Job First (SJF) scheduling algorithm for process execution. The program implements both non-preemptive and preemptive versions of the SJF algorithm. The user can input the details of the processes, including process ID, arrival time, and burst time, and the program calculates various metrics such as completion time, turnaround time, and waiting time.

Getting Started

To run the program, follow these steps:

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Compile the Java file using the following command:
	javac Main.java
3. Run the program using the following command:
	java Main
Usage
Upon running the program, you will be prompted to enter the number of processes. After that, you need to provide the details of each process, including the process ID, arrival time, and burst time.

The program will then calculate the execution order and display the Gantt chart for both the non-preemptive SJF and preemptive SJF algorithms. Additionally, it will provide metrics such as average turnaround time and average waiting time for each algorithm.

Program Structure

The program consists of the following main components:

1. Main class: This is the entry point of the program. It initializes the necessary variables, takes user input, and calls the non-preemptive and preemptive SJF algorithms to calculate the scheduling order. It also displays the Gantt chart for the execution order.
2. ProcessSJF class: This class represents the process data structure and provides methods to input process details, display process data, and calculate average values.
3. NonPreemptiveSJF class: This class extends the ProcessSJF class and implements the non-preemptive SJF algorithm. It sorts the processes based on arrival time and calculates completion time, turnaround time, and waiting time for each process.
4. PreemptiveSJF class: This class extends the ProcessSJF class and implements the preemptive SJF algorithm. It calculates the execution order by continuously selecting the process with the shortest burst time.
Gantt Chart Visualization


The program uses a simple graphical interface to display the Gantt chart. Two separate frames, FrameForNonPreemptiveSJF and FrameForPreemptiveSJF, are created to visualize the execution order for the non-preemptive and preemptive SJF algorithms, respectively. The Gantt chart is drawn using rectangles representing the processes and their execution times.

Please note that the graphical visualization is basic and might not be suitable for large numbers of processes or complex scheduling scenarios.

Feel free to modify the program as needed or enhance the visualization to meet requirements.