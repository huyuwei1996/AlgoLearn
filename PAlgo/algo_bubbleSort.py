def bubbleSort(arr):
    """
    冒泡排序
    """
    # i=[0,len-1)
    # j=[0,len-1-i)

    # i=[1,len)
    # j=[0,len-i)
    for i in range(1, len(arr)):
        for j in range(len(arr) - i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
        # print("第" + str(i) + "轮排序后：")
        # print(arr)
    return arr


def bubbleSortUp(arr):
    """
    冒泡排序优化后，如果没发生交换，退出该此内层循环
    """
    # i=[0,len-1)
    # j=[0,len-1-i)

    # i=[1,len)
    # j=[0,len-i)
    for i in range(1, len(arr)):
        hasChanged = False
        for j in range(0, len(arr) - i):
            if (arr[j] > arr[j + 1]):
                hasChanged = True
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
        if not hasChanged:
            break
        # print("第" + str(i) + "轮排序后：")
        # print(arr)
    return arr


l = [1, 3, 5, 7, 7, 3, 9, 5, 3]
# bubbleSort(l)
bubbleSortUp(l)
