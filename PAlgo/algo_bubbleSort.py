def bubbleSort(arr):
    """
    冒泡排序
    :param list:
    :return:
    """
    # if i: 0～len - 1，j: 0～len - 1 - i
    # if i: 1～len，    j: 0～len - i
    for i in range(1, len(arr)):
        for j in range(0, len(arr) - i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
            # print(arr)
        print(arr)
        print("第" + str(i) + "趟👆")
    return arr


l = [1, 3, 5, 7, 7, 3, 9, 5, 3]
bubbleSort(l)
