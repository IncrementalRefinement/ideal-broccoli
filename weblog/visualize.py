import csv
import matplotlib.pyplot as plt


def main():
    plt.rc('font', family='Youyuan', size='9')
    plot_time_frequency()
    plot_plot_top10_url()
    plot_top10_ip()


def plot_time_frequency():
    times = []
    frequencies = []

    with open("./timeVersusFrequency.csv") as time_frequency:
        csv_reader = csv.reader(time_frequency, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                pass
            else:
                times.append(row[0])
                frequencies.append(row[1])
            line_count += 1

    plt.plot(times, frequencies)
    plt.xlabel(times)
    plt.ylabel(frequencies)
    plt.xlabel("Time")
    plt.ylabel("Frequency")
    plt.title('用户访问频率-时间')
    plt.savefig('time_frequencies.jpg')
    plt.clf()


def plot_plot_top10_url():
    url = []
    count = []

    with open("./top10Url.csv") as time_frequency:
        csv_reader = csv.reader(time_frequency, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                pass
            else:
                url.append(row[0])
                count.append(row[1])
            line_count += 1

    fig, ax = plt.subplots()
    ax.bar(url, count, align='center', width=0.7)
    ax.set_ylabel('Request Frequency')
    ax.set_title('top10_url')
    fig.savefig('top10_url.jpg')
    plt.clf()


def plot_top10_ip():
    ip = []
    count = []

    with open("./top10Ip.csv") as time_frequency:
        csv_reader = csv.reader(time_frequency, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                pass
            else:
                ip.append(row[0])
                count.append(row[1])
            line_count += 1

    fig, ax = plt.subplots()
    ax.bar(ip, count, align='center', width=0.7)
    ax.set_ylabel('Request Frequency')
    ax.set_title('top10_ip')
    fig.savefig('top10_ip.jpg')
    plt.clf()


if __name__ == "__main__":
    main()
