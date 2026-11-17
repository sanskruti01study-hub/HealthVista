<<<<<<< HEAD
def calculate_wellness_score(data):
    """
    Calculates a wellness score out of 100 based on the 7 Health Vista parameters.
    """
    score = 0
    health_issues = [issue.lower() for issue in data.get("existing_health_issues", [])]

    # 1. Sleep (Max 20 pts) - Ideal: 7 to 9 hours
    sleep = data.get("sleep_hours", 0)
    if 7 <= sleep <= 9:
        score += 20
    elif 6 <= sleep < 7 or 9 < sleep <= 10:
        score += 14
    elif 5 <= sleep < 6:
        score += 8
    else:
        score += 4

    # 2. Step Count (Max 20 pts) - Target: 8,000+ steps
    steps = data.get("step_count", 0)
    if steps >= 8000:
        score += 20
    elif steps >= 5000:
        score += 14
    elif steps >= 2500:
        score += 8
    else:
        score += 4

    # 3. Water Intake (Max 15 pts) - Target: 2.5+ Liters
    water = data.get("water_liters", 0)
    if water >= 2.5:
        score += 15
    elif water >= 1.8:
        score += 11
    elif water >= 1.0:
        score += 6
    else:
        score += 3

    # 4. Daily Physical Activity (Max 15 pts) - Target: 30+ mins
    activity = data.get("activity_minutes", 0)
    if activity >= 30:
        score += 15
    elif activity >= 15:
        score += 10
    elif activity > 0:
        score += 5

    # 5. Screen Time (Max 15 pts) - Lower is better (<= 3 hrs ideal)
    screen = data.get("screen_time_hours", 0)
    if screen <= 3:
        score += 15
    elif screen <= 5:
        score += 10
    elif screen <= 7:
        score += 5
    else:
        score += 2

    # 6. Sweet Intake (Max 15 pts) - Stricter scoring if user has diabetes
    sweets = data.get("sweet_intake_servings", 0)
    has_diabetes = any("diabetes" in issue or "sugar" in issue for issue in health_issues)

    if has_diabetes:
        if sweets == 0:
            score += 15
        elif sweets == 1:
            score += 6
        else:
            score += 0  # High penalty for sugar intake with diabetes
    else:
        if sweets == 0:
            score += 15
        elif sweets == 1:
            score += 11
        elif sweets == 2:
            score += 6
        else:
            score += 2

    # Cap score between 0 and 100
    final_score = max(0, min(100, round(score)))
    
    if final_score >= 80:
        status = "Excellent"
    elif final_score >= 60:
        status = "Good"
    elif final_score >= 40:
        status = "Fair"
    else:
        status = "Needs Attention"

    return {
        "score": final_score,
        "status": status
    }
=======
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
>>>>>>> 358c14c368c3c7af094a09200031ff8a9d0d74f2
