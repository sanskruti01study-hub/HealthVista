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