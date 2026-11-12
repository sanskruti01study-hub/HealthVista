import json
from wellness_score import calculate_wellness_score

def main():
    try:
        with open("data/sample_data.json", "r") as f:
            user_data = json.load(f)
    except FileNotFoundError:
        print("Sample data file not found!")
        return

    score = calculate_wellness_score(user_data)
    print(f"--- Health Vista AI Engine ---")
    print(f"Calculated Wellness Score: {score} / 100")

if __name__ == "__main__":
    main()