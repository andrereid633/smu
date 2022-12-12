package com.company;

import java.util.Scanner;

public class CapsuleVolumeSurfaceAreaCircumference {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("This program prints the volume, surface area and circumference of a capsule having radius r \n" +
                "ranging from 6 to N in increments of 6 and side length a");

        final double PI = 3.14159265358979;

        System.out.println("\nEnter value for N: ");
        int n = scanner.nextInt();

        System.out.println("\nEnter the length of side a: ");
        double a = scanner.nextDouble();

        System.out.println("Radius\tVolume\t\t\t\t\t\t\tSurface Area\t\tCircumference");
        System.out.println("-----------------------------------------------------------------------");

        for (int r = 6; r <= n; r+=6) {
            // Volume of a capsule: V = πr2((4/3)r + a)
            // Surface area of a capsule: S = 2πr(2r + a)
            // Circumference of a capsule: C = 2πr

            double volume = PI * (r * r) * ((4f/3f) * r + a);
            double surfaceArea = 2 * PI * r * (2 * r + a);
            double circumference = 2 * PI * r;

            System.out.printf("%d\t\t%.3f                \t\t%.3f  \t\t\t%.3f\n", r, volume, surfaceArea, circumference);
        }
    }
}
