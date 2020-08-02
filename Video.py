import os
import cv2
from PIL import Image

# Checking the current directory path
print(os.getcwd())

# Folder which contains all the images
# from which video is to be generated
path = "./images/"

mean_height = 0
mean_width = 0

num_of_images = len(os.listdir('.'))

for file in os.listdir(path):
    im = Image.open(os.path.join(path, file))
    width, height = im.size
    mean_width += width
    mean_height += height

mean_width = int(mean_width / num_of_images)
mean_height = int(mean_height / num_of_images)


def generate_video():
    image_folder = path  # make sure to use your folder
    video_name = 'video.avi'

    images = [str(x) + '.png' for x in range(1, 101)]

    # Array images should only consider
    # the image files ignoring others if any
    print(images)

    frame = cv2.imread(os.path.join(image_folder, images[0]))

    # setting the frame width, height width
    # the width, height of first image
    height, width, layers = frame.shape

    video = cv2.VideoWriter(video_name, 0, 1, (width, height))

    # Appending the images to the video one by one
    for image in images:
        video.write(cv2.imread(os.path.join(image_folder, image)))

    # Deallocating memories taken for window creation
    cv2.destroyAllWindows()
    video.release()  # releasing the video generated


# Calling the generate_video function
generate_video()
