from singletone import Singletone


class DataManager(Singletone):

    def __init__(self):
        self.json_data = ''
        self.read_data()

    def read_data(self):
        file_directory = './data.json'
        self.json_data = open(file_directory).read()
