############################################
# Moby Ashouri | Maymunah Hicks
############################################

def flip(stack: list[int], num_elements: int) -> list[int]:
    """
    Reverses the order of the first 'num_elements' in 'stack'.
    
    :param stack: List of integers representing the stack to be modified.
    :param num_elements: The number of elements from the start of the list to flip.
    :return: The modified stack with the first 'num_elements' reversed.
    """
    # Slice the stack to get the substack up to 'num_elements'
    substack = stack[:num_elements]
    # Reverse the substack
    reversed_substack = substack[::-1]
    # Replace the original substack with the reversed substack
    stack[:num_elements] = reversed_substack
    
    return stack

def pancake_sort(arr: list[int]) -> tuple[list[int], list[int]]:
    """
    Sorts a list using the pancake sorting algorithm and returns the sorted list and flip positions.
    
    :param arr: List of integers to sort.
    :return: A tuple containing the sorted list and a list of positions where flips were made.
    """
    length_of_array = len(arr)  # Total number of elements in the array
    flips = []  # Store positions where flips are made
    
    # Iterate over the array from the last element to the second
    for size in range(length_of_array, 1, -1):
        # Find the index of the maximum element in the unsorted portion of the array
        max_index = arr.index(max(arr[:size]))
        # Check if the maximum element is not already in its correct position
        if max_index != size - 1:
            # If max element is not at the start, flip it to the start
            if max_index != 0:
                arr = flip(arr, max_index + 1)
                flips.append(max_index + 1)
            # Flip the maximum element to its correct position
            arr = flip(arr, size)
            flips.append(size)
            
    return arr, flips

def main():
    # Open the file 'input.txt' and read its contents
    with open("input.txt", "r") as file:
        lines = file.read().split("\n")  # Split the contents into lines
        
    # Iterate through each line in the file
    for line in lines:
        # Split each line into a list of strings, then convert to integers
        numbers_list = line.split(" ")
        numbers_list = [int(x) for x in numbers_list]
        
        # Sort the list using pancake sort and get the flip positions
        sorted_list, flip_positions = pancake_sort(numbers_list)
        # Adjust flip positions to match the original problem statement's index space
        flip_positions = [(len(sorted_list) - x) + 1 for x in flip_positions if x != 0]
        flip_positions.append(0)  # Append 0 as per the problem's requirement
        
        # Print the sorted list and flip positions
        print("Sorted List:", sorted_list)
        print("Flip Positions (Right to Left):", ' '.join(map(str, flip_positions)))

main()
