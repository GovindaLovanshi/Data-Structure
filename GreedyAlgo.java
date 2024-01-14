import java.util.*;

public class GreedyAlgo {
    static class job {
        int deadline;
        int profit;
        int id;

        public job(int i, int d, int p) {
            id = i;
            deadline = d;
            profit = p;
        }
    }

    public static void main(String args[]) {
        int start[] = { 1, 3, 0, 5, 8, 5 };
        int end[] = { 2, 4, 6, 7, 9, 9 };

        // end time base sorteed tc O(n)

        // sorting O(logn)

        int activities[][] = new int[strt.length][3];

        for (int i = 0; i < start.length; i++) {
            activities[0] = i;
            activities[1] = start[i];
            activities[2] = end[i];
        }

        // lambda function in java --> short form
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));
        int maxActiviti = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        // 1 st Activity
        maxActiviti = 1;
        ans.add(0);
        // sorting arr k liye
        ans.add(activities[0][0]);
        int lastEnd = activities[0][2];

        for (int i = 0; i < end.length; i++) {
            if (activities[i][1] >= lastEnd) {
                // activity select
                maxActiviti++;
                ans.add(activities[i][0]);
                lastEnd = activities[i][2];
            }
        }

        int lastEnd = end[0];
        for (int i = 0; i < end.length; i++) {
            if (start[i] >= lastEnd) {
                // activity select
                maxActiviti++;
                ans.add(i);
                lastEnd = end[i];
            }
        }

        System.out.println("max Activities = " + maxActiviti);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println("A" + ans.get(i));
        }
        System.out.println();

        int val[] = { 60, 100, 120 };
        int weighted[] = { 10, 20, 30 };
        int w = 50;// capacity bake

        double ratio[][] = new double[val.length][2];

        // 0th col => idx 1 col => ratio

        for (int i = 0; i < val.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = val[i] / (double) weighted[i];
        }

        // asecending sort
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

        int capacity = w;
        int finalVal = 0;

        // sort desending

        for (int i = ratio.length - 1; i >= 0; i++) {
            int idx = (int) ratio[i][0];
            if (capacity >= weighted[idx]) {
                // iclude full time
                finalVal += val[idx];
                capacity -= weighted[idx];
            } else {
                // include frictional iteem
                finalVal += (ratio[i][1] * capacity);
                capacity = 0;
                break;
            }
        }

        System.out.println("final Value =" + finalVal);

        int A[] = { 1, 2, 3 };// logn
        int B[] = { 2, 1, 3 };

        Arrays.sort(A);
        Arrays.sort(B);

        int minDiff = 0;

        for (int i = 0; i < A.length; i++) {
            minDiff += Math.abs(A[i] - B[i]);
        }

        System.out.println("MIN DIIFERENCE of Pairs = " + minDiff);// TC O(nlogn)
        // amximum chAIN length pairs

        int pairs[][] = { { 5, 24 }, { 39, 60 }, { 5, 28 }, { 27, 40 }, { 50, 90 } };

        Arrays.sort(pairs, Comparator.comparingDouble(o -> o[1]));// 1st colume base
        // sorting

        int chainLen = 1;
        int chainEnd = pairs[0][1];// last selected pairs end chain end

        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > chainEnd) {// start > end
                chainLen++;
                chainEnd = pairs[i][1];
            }
        }

        System.out.println("MAximum chai length pairs = " + chainLen);// tc O(nlogn)

        // indian coins Tc O(nlogn)

        Integer coins[] = { 1, 2, 5, 10, 20, 50, 100, 500, 2000 };

        Arrays.sort(coins, Comparator.reverseOrder());

        int countOfCoins = 0;
        int amount = 590;
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                while (coins[i] <= amount) {
                    countOfCoins++;
                    ans.add(coins[i]);
                    amount -= coins[i];
                }
            }
        }

        System.out.println("count of coins = " + countOfCoins);

        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();

        // job sequencing problem

        int jobInfo[][] = { { 4, 20 }, { 1, 10 }, { 1, 40 }, { 1, 30 } };

        ArrayList<job> jobs = new ArrayList<>();

        for (int i = 0; i < jobInfo.length; i++) {
            jobs.add(new job(i, jobInfo[i][0], jobInfo[i][1]));
        }

        // collection order of profit
        Collections.sort(jobs, (obj1, obj2) -> obj2.profit - obj1.profit);

        ArrayList<Integer> seq = new ArrayList<>();
        int time = 0;
        for (int i = 0; i < jobs.size(); i++) {
            job curr = jobs.get(i);
            if (curr.deadline > time) {
                seq.add(curr.id);
                time++;
            }
        }

        // print seq
        System.out.println("max jobs =" + seq.size());
        for (int i = 0; i < seq.size(); i++) {
            System.out.println(seq.get(i) + "");
        }
        System.out.println();

        // chocala priblem
        int n = 4, m = 6;// row and colum
        Integer costVerti[] = { 2, 1, 3, 1, 4 };// m-1
        Integer costHori[] = { 4, 1, 2 };// n-1

        // sort in desending order
        Arrays.sort(costVerti, Collections.reverseOrder());
        Arrays.sort(costHori, Collections.reverseOrder());

        int h = 0, v = 0;// h = horizontal cuts v = vertical cuts
        int hp = 1, vp = 1;// hp horizontal pices vp = vert
        int cost = 0;

        while (h < costHori.length && v < costVerti.length) {
            if (costVerti[v] <= costHori[h]) {
                // horizontal cuts
                cost += (costHori[h] * vp);
                hp++;
                h++;
            } else {
                // vertical cuts
                cost += (costVerti[v] * hp);
                vp++;
                v++;
            }
        }

        // bache hue vert pr hori cuts
        while (h < costHori.length) {
            cost += (costHori[h] * vp);
            hp++;
            h++;
        }

        while (v < costVerti.length) {
            cost += (costVerti[v] * hp);
            vp++;
            v++;
        }

        System.out.println("minimum cost cuts = " + cost);
    }
}
