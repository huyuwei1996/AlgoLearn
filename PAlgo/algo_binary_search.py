import math


def binary_search(list, item):
    """
    二分查找
    :param list:有序数组
    :param item:需要查找的元素
    :return:
    """
    low = 0
    high = len(list) - 1
    while low <= high:
        mid = math.floor((low + high) / 2)  # 如果不是偶数，就向下取整
        guess = list[mid]
        if guess == item:  # 如果找到了，直接返回
            return mid
        if guess > item:  # 如果猜大了
            high = mid - 1
        else:  # 如果猜小了
            low = mid + 1
    return None


my_list = [1, 3, 5, 7, 9]
print(binary_search(my_list, 3))
print(binary_search(my_list, -1))
