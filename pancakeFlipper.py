############################################
# Moby Ashouri | Maymunah Hicks
############################################
def flip(original_stack: list[int], number):
    substack = original_stack[:number]
    reversed_substack = substack[::-1]
    original_stack[:number] = reversed_substack
    
    return original_stack

def pancake_sort(arr):
    lengthOfArray = len(arr)
    flips = []
    for size in range(lengthOfArray, 1, -1):
        max_index = arr.index(max(arr[:size]))
        if max_index != size - 1:
            if max_index != 0:
                arr = flip(arr, max_index + 1)
                flips.append(max_index + 1)
            arr = flip(arr, size)
            flips.append(size)
            
    return arr, flips

def main():
    with open("input.txt", "r") as file:
        lines = file.read().split("\n")
        
    for line in lines:
        lst = line.split(" ")
        lst = [int(x) for x in lst]
        
        lst, flip_positions = pancake_sort(lst)
        flip_positions = [(len(lst) - x)+1 for x in flip_positions if x != 0]  # Convert flip positions to original index space
        flip_positions.append(0)
        
        print("Sorted List:", lst)
        print("Flip Positions (Right to Left):", ' '.join(map(str, flip_positions)))

main()
