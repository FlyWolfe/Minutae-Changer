from Tkinter import *
#from PIL import ImageTK, Image
#import os

master = Tk()

def var_states():
   print("Delete: %d" % (var1.get()))

Label(master, text="Delete Minutiae").grid(row=0, sticky=W)
var1 = IntVar()
Checkbutton(master, text="Delete", variable=var1).grid(row=1, sticky=W)
Button(master, text='Show', command=var_states).grid(row=4, sticky=W, pady=4)

#img = ImageTK.PhotoImage(Image.open("/home/brian/workspace/finger.jpg")
#panel = Label(master, image = img)
#panel.pack(side = "bottom", fill = "both", expand = "yes")
mainloop()
