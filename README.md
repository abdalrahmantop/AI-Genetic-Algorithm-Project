# 🧬 32-bit Passcode Breaker using Genetic Algorithm

This project is an implementation of a **Genetic Algorithm (GA)** designed to crack a randomly generated 32-bit passcode. Developed as part of the Artificial Intelligence course (**COMP338**) at **Birzeit University**.

## 📸 Project Preview
![Project Screenshot](screenshot.png) 

## 🧐 Overview
The goal of this project is to demonstrate the efficiency of evolutionary search compared to exhaustive brute-force. Instead of trying all $2^{32}$ combinations, the algorithm evolves a population of candidate solutions toward the global optimum using biological principles.

## 🚀 Key Features
- **JavaFX GUI:** A user-friendly interface to input parameters and visualize results.
- **Real-time Convergence Plot:** A dynamic line chart showing the improvement of the best fitness score over generations.
- **Performance Metrics:** Tracking of "Actual Time" in milliseconds and the total number of generations to reach the target.
- **Dynamic Tuning:** Ability to modify Population Size and Mutation Rate.

## 🛠 Genetic Algorithm Logic
The implementation follows these core stages:

1. **Initialization:** Generates a random population of 32-bit binary strings.
2. **Fitness Evaluation:** Calculates the score based on the number of bits matching the target (Hamming Distance).
3. **Selection (Elitism):** Selects the top two performing individuals to act as parents for the next generation.
4. **Crossover:** Uses **Single-point Crossover** to combine genetic material from parents.
5. **Mutation:** Applies a **Bit-flip Mutation** to prevent local optima and maintain genetic diversity.



## 📊 Sample Result
| Parameter | Value |
| :--- | :--- |
| **Population Size** | 100 |
| **Mutation Rate** | 0.01 |
| **Generations** | ~120 - 200 |
| **Actual Time** | < 100 ms |

## 💻 How to Run
1. Ensure you have **Java 17+** and **JavaFX SDK** installed.
2. Clone the repository:
   ```bash
   git clone [https://github.com/YourUsername/RepoName.git](https://github.com/YourUsername/RepoName.git)
