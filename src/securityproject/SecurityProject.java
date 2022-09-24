/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.*;

/**
 *
 * @author leele
 */
public class SecurityProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        int choice = 3;
        int choice2 = 3;

        do {
            System.out.println("MAIN MENU");
            System.out.println("====================================================");
            System.out.println("What do you need to implement?");
            System.out.println("1. Encryption\n2. Hashing\n3. Exit\n");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            if (choice == 3) {
                System.exit(0);

            }
            LinkedList<String> fileList = new LinkedList<>();

            switch (choice) {

                case 1:
                    System.out.println("1. Encrypt\n2. Decrypt\n3. Back to main menu");

                    System.out.println("----------------------------------------------------");
                    System.out.print("Ente your choice: ");
                    choice2 = input.nextInt();
                    if (choice2 == 3) {
                        break;
                    }
                    System.out.println("(1) File (2) Folder");
                    System.out.print("Ente your choice: ");
                    int choice3 = input.nextInt();
                    String fName = null;

                    if (choice3 == 1) {
                        System.out.print("Type your file name: ");
                        fName = input.next();

                    } else if (choice3 == 2) {
                        System.out.print("Type your folder name: ");
                        fName = input.next();

                        Files.list(new File(fName).toPath())
                                .limit(50)
                                .forEach(path -> {
                                    //System.out.println(path);
                                    String fn = path + "";
                                    if (!(fn.contains(".encrypted"))) {
                                        fileList.add(fn);
                                    }
                                });

                    }
                    System.out.print("Choose the algorthim (AES, DES): ");
                    String algo = input.next();

                    try {

                        System.out.print("Enter the secret key: ");
                        String key = input.next();

                        if (choice2 == 1) {
                            if (choice3 == 1) {
                                File inputFile = new File(fName);
// to encrypt
                                String s = fName.replace(".txt", "");
                                File encryptedFile = new File(s + ".encrypted");
// to decrypt

                                Crypto.encrypt(key, inputFile, encryptedFile, algo);

                                System.out.println("----------------------------------------------------");
                                if (algo.equals("AES")) {
                                    System.out.println("Done! File " + fName + " is encrypted using " + algo + "-192");
                                } else if (algo.equals("DES")) {
                                    System.out.println("Done! File " + fName + " is encrypted using " + algo + "-64");
                                }
                                System.out.println("Output file is " + s + ".encrypted");
                                System.out.println("----------------------------------------------------");
                            } else if (choice3 == 2) {

                                for (String fn : fileList) {
                                    File f = new File(fn);
                                    String s = fn.replace(".txt", "");
                                    File encryptedFile = new File(s + ".encrypted");

                                    Crypto.encrypt(key, f, encryptedFile, algo);

                                }
                                System.out.println("----------------------------------------------------");
                                if (algo.equals("AES")) {
                                    System.out.println("Done! Folder " + fName + " is encrypted using " + algo + "-192");
                                } else if (algo.equals("DES")) {
                                    System.out.println("Done! Folder " + fName + " is ecncrypted using " + algo + "-64");
                                }
                                //System.out.println("Output file is " + fName + ".encrypted");
                                System.out.println("----------------------------------------------------");

                            }

                        } else if (choice2 == 2) {
                            if (choice3 == 1) {
                                String s = fName.replace(".txt", "");
                                File encryptedFile = new File(s + ".encrypted");
                                File decryptedFile = new File(s + ".decrypted");

                                Crypto.decrypt(key, encryptedFile, decryptedFile, algo);
                                System.out.println("----------------------------------------------------");
                                if (algo.equals("AES")) {
                                    System.out.println("Done! File " + fName + " is decrypted using " + algo + "-192");
                                } else if (algo.equals("DES")) {
                                    System.out.println("Done! File " + fName + " is decrypted using " + algo + "-64");
                                }
                                System.out.println("Output file is " + s + ".decrypted");
                                System.out.println("----------------------------------------------------");
                            } else if (choice3 == 2) {

                                for (String fn : fileList) {
                                    String s = fn.replace(".txt", "");
                                    File encryptedFile = new File(s + ".encrypted");
                                    File decryptedFile = new File(s + ".decrypted");

                                    Crypto.decrypt(key, encryptedFile, decryptedFile, algo);
                                }
                                System.out.println("----------------------------------------------------");
                                if (algo.equals("AES")) {
                                    System.out.println("Done! Folder " + fName + " is decrypted using " + algo + "-192");
                                } else if (algo.equals("DES")) {
                                    System.out.println("Done! Folder " + fName + " is decrypted using " + algo + "-64");
                                }
                                //System.out.println("Output file is " + fName + ".decrypted");
                                System.out.println("----------------------------------------------------");

                            }

                        } else if (choice2 == 3) {

                            break;
                        }

                    } catch (CryptoException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Type your file name: ");
                    String fn = input.next();
                    String s = fn.replace(".txt", "");
                    File hashedFile = new File(s + ".msgdigest");
                    File inputFile = new File(fn);
                    System.out.print("Choose the algorthim (SHA-256, SHA-512): ");
                    algo = input.next();

                    Hashing.getHash(inputFile, hashedFile, algo);

                    System.out.println("\nDone! The message digest of the file " + fn + " is generated using " + algo);
                    System.out.println("Output file is " + hashedFile);

                    break;
                case 3:
                    System.exit(0);

            }
            System.out.println();
        } while (choice != 3 || choice2 == 3);

    }

}
