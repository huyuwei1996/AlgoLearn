def findSmallest(arr: list):
    """
    返回最小值下标
    :param arr:
    :return:
    """
    smallest = arr[0]  # 假定第一个值是最小的
    smallest_index = 0
    for i in range(1, len(arr)):  # 从第二个数到最后一个数，和第一个数比大小，把小的记录下来
        if arr[i] < smallest:
            smallest = arr[i]
            smallest_index = i
    return smallest_index


def selectionSort(arr: list):
    """
    选择排序
    :param arr:传入一个数组
    :return:
    """
    newArr = []
    for i in range(len(arr)):
        smallest = findSmallest(arr)
        newArr.append(arr.pop(smallest))
        print(newArr)
    return newArr


l = [5, 3, 6, 2, 10]
print(selectionSort(l))
