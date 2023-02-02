export const stringToSeparator = (amount: string) => {
  return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};
