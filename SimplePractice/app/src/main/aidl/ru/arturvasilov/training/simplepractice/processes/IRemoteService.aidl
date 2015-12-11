package ru.arturvasilov.training.simplepractice.processes;

interface IRemoteService {

    int getPid();

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
