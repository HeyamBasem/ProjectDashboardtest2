package server.controller;

import operations.Constants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static IdGenerator instance;

    private AtomicInteger userId;
    private AtomicInteger licenseId;
    private AtomicInteger journalId;
    private AtomicInteger contentId;

    private IdGenerator() {
        initialize();
    }

    private void initialize() {
        try (Scanner sc = new Scanner(new FileInputStream(Constants.ID_FILE))) {
            initializeWithException(sc);
        } catch (Exception e) {
        }
    }

    private void initializeWithException(Scanner sc) throws Exception {
        this.userId = new AtomicInteger(sc.nextInt());
        this.licenseId = new AtomicInteger(sc.nextInt());
        this.journalId = new AtomicInteger(sc.nextInt());
        this.contentId = new AtomicInteger(sc.nextInt());
    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    private void updateFile() {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(Constants.ID_FILE))) {
            updateFileWithException(printWriter);
        } catch (Exception e) {
        }
    }

    private void updateFileWithException(PrintWriter printWriter) {
        printWriter.println(userId);
        printWriter.println(licenseId);
        printWriter.println(journalId);
        printWriter.println(contentId);
        printWriter.flush();
    }

    public int createUserIdentity() {
        int id = userId.getAndIncrement();
        updateFile();
        return id;
    }

    public int createLicenseIdentity() {
        int id = licenseId.getAndIncrement();
        updateFile();
        return id;
    }

    public int createJournalIdentity() {
        int id = journalId.getAndIncrement();
        updateFile();
        return id;
    }

    public int createContentIdentity() {
        int id = contentId.getAndIncrement();
        updateFile();
        return id;
    }
}
