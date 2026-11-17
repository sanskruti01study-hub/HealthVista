def calculate_wellness_score(today_data):
    steps = today_data.get("steps", 0)
    sleep = today_data.get("sleep_hours", 0)
    screen_time = today_data.get("screen_time_hours", 0)
    water = today_data.get("water_intake_liters", 0)
    sweets = today_data.get("sweet_intake_grams", 0)

    step_score = min(25, (steps / 10000) * 25)
    sleep_score = min(25, (sleep / 8) * 25)
    water_score = min(20, (water / 3) * 20)
    
    screen_penalty = max(0, (screen_time - 4) * 3)
    sweet_penalty = max(0, (sweets - 50) / 10 * 2)
    
    lifestyle_score = max(0, min(100, (step_score + sleep_score + water_score) - (screen_penalty + sweet_penalty) + 30))
    
    return {
        "wellness_score": round(lifestyle_score, 2),
        "category": "Excellent" if lifestyle_score >= 80 else "Good" if lifestyle_score >= 60 else "Needs Improvement"
    }