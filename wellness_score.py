def calculate_wellness_score(user_data):
    """
    Calculates a wellness score out of 100 based on lifestyle parameters.
    """
    # Simple calculation logic based on sample data parameters
    steps = user_data.get("steps", 0)
    sleep_hours = user_data.get("sleep_hours", 7)
    water_liters = user_data.get("water_liters", 2)
    screen_time = user_data.get("screen_time_hours", 4)
    sweet_intake = user_data.get("sweet_intake_servings", 1)

    # Base scoring logic
    score = 50
    if steps >= 8000:
        score += 15
    elif steps >= 5000:
        score += 10
        
    if sleep_hours >= 7:
        score += 15
    else:
        score -= 5
        
    if water_liters >= 2:
        score += 10
        
    # Deductions for negative habits
    if screen_time > 6:
        score -= 10
    if sweet_intake > 2:
        score -= 10

    # Ensure score stays between 0 and 100
    return max(0, min(100, score))