export const stringToSeparator = (amount: string) => {
  const originAmount = amount.replaceAll(',', '');
  return originAmount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};

export const stringToNumber = (amount: string) => {
  const removedSeparator = amount.replaceAll(',', '');
  return Number(removedSeparator);
};
