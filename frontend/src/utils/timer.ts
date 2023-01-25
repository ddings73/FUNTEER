export const milliSecondsToHours = (milliSeconds: number): number => {
  return (milliSeconds / (1000 * 60 * 60)) % 24;
};

export const milliSecondsToMinutes = (milliSeconds: number): number => {
  return (milliSeconds / (1000 * 60)) % 60;
};

export const milliSecondsToSeconds = (milliSeconds: number): number => {
  return (milliSeconds / 1000) % 60;
};

export const secondsToHours = (seconds: number): number => {
  return seconds / 3600;
};

export const secondsToMinutes = (seconds: number): number => {
  return (seconds % 3600) / 60;
};

export const secondsToSeconds = (seconds: number): number => {
  return seconds % 60;
};
