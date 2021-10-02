// IAdditionService.aidl
package com.xcaosta.boundsvc;

// basicTypes: boolean, int, long, float, double, String
// Declare any non-default types here with import statements
interface IAdditionService {
    // You can pass values in, out, or inout.
    // Primitive datatypes (such as int, boolean, etc.) can only be passed in.
    int add(in int value1, in int value2);
}