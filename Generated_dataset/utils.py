import string
import random


def pincode_gen(size=6, chars=string.digits):
    return ''.join(random.choice(chars) for x in range(size))
