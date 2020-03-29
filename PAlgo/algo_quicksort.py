def quicksort(array: list):
    """
    快速排序
    :param array:要排序的数组
    :return:
    """
    if len(array) < 2:
        return array  # 基线条件：为空或只包含一个元素的数组是有序的
    else:
        pivot = array[0]  # 递归条件
        less = [i for i in array[1:] if i <= pivot]  # 从第二个值开始遍历，把小于基准值的存到子数组
        greater = [i for i in array[1:] if i > pivot]  # 从第二个值开始遍历，把大于基准值的存到子数组
        print(less, pivot, greater)
        return quicksort(less) + [pivot] + quicksort(greater)


arr = [10, 5, 2, 3]
print(quicksort(arr))